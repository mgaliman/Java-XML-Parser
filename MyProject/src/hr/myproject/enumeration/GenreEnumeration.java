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
public enum GenreEnumeration {
    Akcija("akcija"),
    Animirani("animirani"),
    AkcioniTriler("akcioni triler"),
    Avantura("avantura"),
    Dokumentarni("dokumentarni"),
    Drama("drama"),
    Krimi("krimi"),
    Komedija("komedija"),
    Ljubavni("ljubavni"),
    Porodicni("porodični"),
    SF("SF"),
    Triler("triler"),
    Horor("horor");
    
    private final String name;

    private GenreEnumeration(String name) {
        this.name = name;
    }    
    
    public static Optional<GenreEnumeration> from(String name){
        for (GenreEnumeration value : values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
    
}
