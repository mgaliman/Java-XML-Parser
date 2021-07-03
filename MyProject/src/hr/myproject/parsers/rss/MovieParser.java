/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.parsers.rss;

import hr.myproject.enumeration.TagType;
import hr.myproject.model.Movie;
import hr.myproject.model.Person;
import hr.myutilities.factory.ParserFactory;
import hr.myutilities.factory.UrlConnectionFactory;
import hr.myutilities.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author mgali
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private MovieParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException, ParseException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            List<Person> persons = null;
            Person person = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);                        
                        switch (tagType.get()) {
                                case ITEM:                                    
                                    movie = new Movie();                                    
                                    movies.add(movie);
                                    break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {                                
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDateTime publisheDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPublishedDate(publisheDate);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(data.substring(data.lastIndexOf("\">") + 2, data.lastIndexOf("<")).trim());
                                    }
                                    break;
                                case ORG_TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                    break;
                                case DIRECTOR:
                                    persons = new ArrayList<Person>();
                                    person = new Person();
                                    if (movie != null && !data.isEmpty()) {
                                        person.setFirstName(data.substring(0, data.lastIndexOf(" ")));
                                        person.setLastName(data.substring(data.lastIndexOf(" ")).trim());

                                        System.out.println(person);
                                        persons.add(person);
                                    }
                                    break;
                                case ACTORS:
                                    persons = new ArrayList<Person>();
                                    person = new Person();
                                    if (movie != null && !data.isEmpty()) {
                                        String personFirstName = data.substring(0, data.indexOf(' '));
                                        String personLastName = data.substring(data.indexOf(' '), data.indexOf(',')).trim();

                                        person.setFirstName(personFirstName);
                                        person.setLastName(personLastName);

                                        System.out.println(person);

                                        persons.add(person);
                                    }
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDuration(Integer.parseInt(data));
                                    }
                                    break;
                                case GENRE:
                                    break;
                                case PICTURE_PATH:
                                    if (movie != null && startElement != null && movie.getPicturePath() == null) {
                                        Attribute urlAtribute = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                                        if (urlAtribute != null) {
                                            handlePicture(movie, urlAtribute.getValue());
                                        }
                                    }
                                    break;
                                case LINK:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setLink(data);
                                    }
                                    break;
                                case START_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        DateFormat formatter = new SimpleDateFormat("d.m.yyyy");

                                        movie.setStartDate(formatter.parse(data));
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        return movies;
    }

    private static void handlePicture(Movie movie, String pictureUrl) {
        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            movie.setPicturePath(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
