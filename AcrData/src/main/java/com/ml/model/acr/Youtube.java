
package com.ml.model.acr;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vid"
})
@Generated("jsonschema2pojo")
public class Youtube {

    @JsonProperty("vid")
    private String vid;

    @JsonProperty("vid")
    public String getVid() {
        return vid;
    }

    @JsonProperty("vid")
    public void setVid(String vid) {
        this.vid = vid;
    }

}
