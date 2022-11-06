package com.bvar.GOTproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url",
        "name",
        "region",
        "currentLord",
        "currentlord"
})
@Data
public class House {

    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private String region;
    @JsonProperty(value = "currentLord",access = JsonProperty.Access.WRITE_ONLY)
    private String currentLordUrl;
    @JsonProperty("currentlord")
    @JsonInclude
    private Lord lord;
}
