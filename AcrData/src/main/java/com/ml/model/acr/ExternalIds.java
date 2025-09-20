
package com.ml.model.acr;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "isrc",
    "upc"
})
@Generated("jsonschema2pojo")
public class ExternalIds {

    @JsonProperty("isrc")
    private List<String> isrc = null;
    @JsonProperty("upc")
    private List<String> upc = null;

    @JsonProperty("isrc")
    public List<String> getIsrc() {
        return isrc;
    }

    @JsonProperty("isrc")
    public void setIsrc(List<String> isrc) {
        this.isrc = isrc;
    }

    @JsonProperty("upc")
    public List<String> getUpc() {
        return upc;
    }

    @JsonProperty("upc")
    public void setUpc(List<String> upc) {
        this.upc = upc;
    }

}
