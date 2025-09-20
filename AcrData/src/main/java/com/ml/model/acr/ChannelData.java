
package com.ml.model.acr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "uid",
    "project_id",
    "id",
    "type",
    "name",
    "sub_title",
    "language",
    "city",
    "province",
    "country",
    "continent",
    "website",
    "twitter",
    "mytuner",
    "urls",
    "status_code",
    "monitor_at",
    "timemap",
    "timemap_lifecycle",
    "user_defined",
    "add_at"
})
@Generated("jsonschema2pojo")
public class ChannelData {

    @JsonProperty("uid")
    private Long uid;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sub_title")
    private String subTitle;
    @JsonProperty("language")
    private String language;
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("country")
    private String country;
    @JsonProperty("continent")
    private String continent;
    @JsonProperty("website")
    private String website;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("mytuner")
    private String mytuner;
    @JsonProperty("urls")
    private List<Url> urls = null;
    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("monitor_at")
    private String monitorAt;
    @JsonProperty("timemap")
    private Integer timemap;
    @JsonProperty("timemap_lifecycle")
    private Integer timemapLifecycle;
    @JsonProperty("user_defined")
    private List<Object> userDefined = null;
    @JsonProperty("add_at")
    private String addAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uid")
    public Long getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(Long uid) {
        this.uid = uid;
    }

    @JsonProperty("project_id")
    public Long getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("sub_title")
    public String getSubTitle() {
        return subTitle;
    }

    @JsonProperty("sub_title")
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("province")
    public String getProvince() {
        return province;
    }

    @JsonProperty("province")
    public void setProvince(String province) {
        this.province = province;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("continent")
    public String getContinent() {
        return continent;
    }

    @JsonProperty("continent")
    public void setContinent(String continent) {
        this.continent = continent;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("twitter")
    public String getTwitter() {
        return twitter;
    }

    @JsonProperty("twitter")
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @JsonProperty("mytuner")
    public String getMytuner() {
        return mytuner;
    }

    @JsonProperty("mytuner")
    public void setMytuner(String mytuner) {
        this.mytuner = mytuner;
    }

    @JsonProperty("urls")
    public List<Url> getUrls() {
        return urls;
    }

    @JsonProperty("urls")
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    @JsonProperty("status_code")
    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonProperty("status_code")
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("monitor_at")
    public String getMonitorAt() {
        return monitorAt;
    }

    @JsonProperty("monitor_at")
    public void setMonitorAt(String monitorAt) {
        this.monitorAt = monitorAt;
    }

    @JsonProperty("timemap")
    public Integer getTimemap() {
        return timemap;
    }

    @JsonProperty("timemap")
    public void setTimemap(Integer timemap) {
        this.timemap = timemap;
    }

    @JsonProperty("timemap_lifecycle")
    public Integer getTimemapLifecycle() {
        return timemapLifecycle;
    }

    @JsonProperty("timemap_lifecycle")
    public void setTimemapLifecycle(Integer timemapLifecycle) {
        this.timemapLifecycle = timemapLifecycle;
    }

    @JsonProperty("user_defined")
    public List<Object> getUserDefined() {
        return userDefined;
    }

    @JsonProperty("user_defined")
    public void setUserDefined(List<Object> userDefined) {
        this.userDefined = userDefined;
    }

    @JsonProperty("add_at")
    public String getAddAt() {
        return addAt;
    }

    @JsonProperty("add_at")
    public void setAddAt(String addAt) {
        this.addAt = addAt;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
