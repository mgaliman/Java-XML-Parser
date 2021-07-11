/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mgali
 */
public class MovieTable extends AbstractTableModel {

    private List<Movie> movies;

    private static final String[] COLUMN_NAMES = {"Id", "Title", "Published date", "Description", "Original title", "Director", "Actors", "Duration", "Genre", "Picture path"};

    public MovieTable(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return Movie.class.getDeclaredFields().length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getPublishedDate().format(Movie.DATE_FORMATTER);
            case 3:
                return movies.get(rowIndex).getDescription();
            case 4:
                return movies.get(rowIndex).getOriginalTitle();
            case 5:
                return movies.get(rowIndex).getDirector();
            case 6:
                return movies.get(rowIndex).getActors();
            case 7:
                return movies.get(rowIndex).getDuration();
            case 8:
                return movies.get(rowIndex).getGenre();
            case 9:
                return movies.get(rowIndex).getPicturePath();
        }

        throw new RuntimeException("No column");
    }
}
