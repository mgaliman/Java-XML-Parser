/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.enumeration;

import java.util.Optional;

/**
 *
 * @author mgali
 */
public enum TagType {
    ITEM("item"),
    TITLE("title"),
    PUB_DATE("pubDate"),
    DESCRIPTION("description"),
    ORG_TITLE("orignaziv"),
    DIRECTOR("redatelj"),
    ACTORS("glumci"),
    DURATION("trajanje"),
    GENRE("zanr"),
    PICTURE_PATH("plakat");
    
    private final String name;

    private TagType(String name) {
        this.name = name;
    }
    
    public static Optional<TagType> from(String name){
        for (TagType value : values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
