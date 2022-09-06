package com.gft.moviesapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CastCrew {
    public List<Cast> casts;
    public List<Crew> crews;
    public CastCrew() {

    }

    public CastCrew(List<Cast> casts, List<Crew> crews) {
        this.casts = casts;
        this.crews = crews;
    }


    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public String toString() {
        return "CastCrew{" +
                "casts=" + casts +
                ", crews=" + crews +
                '}';
    }
}
