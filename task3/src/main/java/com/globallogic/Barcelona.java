package com.globallogic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Barcelona {

    private String id;
    private String listing_url;

    private String name;
    private String latitude;
    private String longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barcelona barcelona = (Barcelona) o;

        return id.equals(barcelona.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
