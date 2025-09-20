
package com.ml.model.acr;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "album",
    "track",
    "artists"
})
@Generated("jsonschema2pojo")
public class Spotify {

    @JsonProperty("album")
    private Album__1 album;
    @JsonProperty("track")
    private Track track;
    @JsonProperty("artists")
    private List<Artist__1> artists = null;

    @JsonProperty("album")
    public Album__1 getAlbum() {
        return album;
    }

    @JsonProperty("album")
    public void setAlbum(Album__1 album) {
        this.album = album;
    }

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track track) {
        this.track = track;
    }

    @JsonProperty("artists")
    public List<Artist__1> getArtists() {
        return artists;
    }

    @JsonProperty("artists")
    public void setArtists(List<Artist__1> artists) {
        this.artists = artists;
    }

}
