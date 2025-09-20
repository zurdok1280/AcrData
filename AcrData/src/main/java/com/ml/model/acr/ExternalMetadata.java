
package com.ml.model.acr;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "spotify",
    "deezer",
    "youtube"
})
@Generated("jsonschema2pojo")
public class ExternalMetadata {

    @JsonProperty("spotify")
    private List<Spotify> spotify = null;
    @JsonProperty("deezer")
    private List<Deezer> deezer = null;
    @JsonProperty("youtube")
    private List<Youtube> youtube = null;

    @JsonProperty("spotify")
    public List<Spotify> getSpotify() {
        return spotify;
    }

    @JsonProperty("spotify")
    public void setSpotify(List<Spotify> spotify) {
        this.spotify = spotify;
    }

    @JsonProperty("deezer")
    public List<Deezer> getDeezer() {
        return deezer;
    }

    @JsonProperty("deezer")
    public void setDeezer(List<Deezer> deezer) {
        this.deezer = deezer;
    }

    @JsonProperty("youtube")
    public List<Youtube> getYoutube() {
        return youtube;
    }

    @JsonProperty("youtube")
    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }

}
