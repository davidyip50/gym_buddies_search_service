package search.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchGymbuddyRequestModel extends RequestModel{
    private String location;
    private String gym;
    private String fitness;
    private int offset;
    private int limit;

    public SearchGymbuddyRequestModel(@JsonProperty(value = "location",required = true) String location,
                                      @JsonProperty(value = "gym") String gym,
                                      @JsonProperty(value = "fitness") String fitness,
                                      @JsonProperty(value = "offset",required = true) int offset,
                                      @JsonProperty(value = "limit",required = true) int limit) {
        this.location = location;
        this.gym = gym;
        this.fitness = fitness;
        this.limit = limit;
        this.offset = offset;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("gym")
    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    @JsonProperty("fitness")
    public String getFitness() {
        return fitness;
    }

    public void setFitness(String fitness) {
        this.fitness = fitness;
    }

    @JsonProperty("offset")
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @JsonProperty("limit")
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
