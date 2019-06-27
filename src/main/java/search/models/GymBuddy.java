package search.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import search.logger.ServiceLogger;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GymBuddy {
    private String name;
    private String gym;
    private String location;
    private String biography;
    private String music;
    private String fitness;

    public GymBuddy(@JsonProperty(value = "name", required = true) String name,
                    @JsonProperty(value = "location", required = true) String location,
                    @JsonProperty(value = "gym") String gym,
                    @JsonProperty(value = "biography") String biography,
                    @JsonProperty(value = "music") String music,
                    @JsonProperty(value = "fitness") String fitness) {
        this.name = name;
        this.location = location;
        this.biography = biography;
        this.music = music;
        this.fitness = fitness;
        this.gym = gym;
    }

    @JsonProperty(value = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("biography")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @JsonProperty("music")
    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @JsonProperty("fitness")
    public String getFitness() {
        return fitness;
    }

    public void setFitness(String fitness) {
        this.fitness = fitness;
    }

    @JsonProperty("gym")
    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public static GymBuddy[] arrayListTwoArray(ArrayList<GymBuddy> arrayList)
    {
        if(arrayList.size() == 0)
        {
            return null;
        }

        GymBuddy[] result = new GymBuddy[arrayList.size()];

        for(int i = 0; i < arrayList.size(); ++i)
        {
            ServiceLogger.LOGGER.info(arrayList.get(i).getName());
            result[i] = arrayList.get(i);
        }
        return result;
    }
}
