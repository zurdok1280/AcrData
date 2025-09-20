
package com.ml.model.acr;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "result_type",
    "metadata"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("status")
    private Status status;
    @JsonProperty("result_type")
    private long resultType;
    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("result_type")
    public long getResultType() {
        return resultType;
    }

    @JsonProperty("result_type")
    public void setResultType(long resultType) {
        this.resultType = resultType;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
