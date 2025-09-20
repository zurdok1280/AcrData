
package com.ml.model.acr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "timestamp_utc",
    "played_duration",
    "music",
    "record_timestamp"
})
@Generated("jsonschema2pojo")
public class Metadata {

    @JsonProperty("type")
    private String type;
    @JsonProperty("timestamp_utc")
    private String timestampUtc;
    @JsonProperty("played_duration")
    private long playedDuration;
    @JsonProperty("music")
    private List<Music> music = null;
    @JsonProperty("record_timestamp")
    private String recordTimestamp;
 
    
    



	@JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("timestamp_utc")
    public String getTimestampUtc() {
        return timestampUtc;
    }

    @JsonProperty("timestamp_utc")
    public void setTimestampUtc(String timestampUtc) {
        this.timestampUtc = timestampUtc;
    }

    @JsonProperty("played_duration")
    public long getPlayedDuration() {
        return playedDuration;
    }

    @JsonProperty("played_duration")
    public void setPlayedDuration(long playedDuration) {
        this.playedDuration = playedDuration;
    }

    @JsonProperty("music")
    public List<Music> getMusic() {
        return music;
    }

    @JsonProperty("music")
    public void setMusic(List<Music> music) {
        this.music = music;
    }

    @JsonProperty("record_timestamp")
    public String getRecordTimestamp() {
    	
        return recordTimestamp;
    }

    @JsonProperty("record_timestamp")
    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }
    
    public LocalDateTime sgetDateRecord() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime dateTime = LocalDateTime.parse(this.recordTimestamp, formatter);
		return dateTime;
    }
    
    
    

}
