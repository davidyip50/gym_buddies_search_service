package search.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import search.MovieService;
import search.exceptions.ModelValidationException;
import search.logger.ServiceLogger;
import search.models.GymBuddy;
import search.models.SearchGymbuddyReponseModel;
import search.models.SearchGymbuddyRequestModel;
import search.utilities.ModelValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("search")
public class search {
    @Path("gymbuddy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@Context HttpHeaders headers, @QueryParam("location") String location,
                           @QueryParam("gym") String gym, @QueryParam("fitness") String fitness,
                           @QueryParam("limit") int limit, @QueryParam("offset") int offset)
    {
        String stringJson = createJsonString(location,gym,fitness,limit,offset);
        try {
            //ObjectMapper objectMapper = new ObjectMapper();

            //JsonNode rootNode = objectMapper.readTree(stringJson);
            SearchGymbuddyRequestModel requestModel = (SearchGymbuddyRequestModel) ModelValidator.verifyModel(stringJson,
                    SearchGymbuddyRequestModel.class);
            return searchUser(requestModel);
        } catch (ModelValidationException e) {
            return ModelValidator.returnInvalidRequest(e, SearchGymbuddyReponseModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    private String createJsonString(String location, String gym, String fitness, Integer limit, Integer offset)
    {
        String jsonString = "{";
        if(location != null)
        {
            jsonString += "\"location\": \"" + location + "\",";
        }
        //else need to get current user location
        if(gym != null)
        {
            jsonString += "\"gym\": \"" + gym + "\",";
        }
        if(fitness != null)
        {
            jsonString += "\"fitness\": \"" + fitness + "\",";
        }
        if( offset != null && offset >= 0)
        {
            jsonString += "\"offset\": " + offset + ",";
        }
        else
        {
            ServiceLogger.LOGGER.info("recieved offset of:" + offset);
            jsonString += "\"offset\": " + 0 + ",";
        }
        if( limit != null && ( limit == 10 || limit == 25 || limit == 50 || limit == 100))
        {
            jsonString += "\"limit\": " + limit ;
        }
        else
        {
            jsonString += "\"limit\": " + 10 ;
        }
        jsonString += "}";
        ServiceLogger.LOGGER.info("json string:" + jsonString);
        return jsonString;
    }

    private Response searchUser(SearchGymbuddyRequestModel requestModel) throws SQLException {
        String query = "SELECT email, location, gym, biography, fitness, music\n\n" +
                "FROM gymdb.users as u\n" +
                "WHERE u.location like ? AND  (? is NULL OR ? LIKE u.gym)  AND (? is NULL OR ? LIKE u.fitness)\n" +
                "LIMIT ? OFFSET ?;";

        PreparedStatement ps = MovieService.getCon().prepareStatement(query);

        ps.setString(1,requestModel.getLocation());
        ps.setString(2,requestModel.getGym());
        ps.setString(3,requestModel.getGym());

        ps.setString(4, requestModel.getFitness());
        ps.setString(5, requestModel.getFitness());

        ps.setInt(6, requestModel.getLimit());
        ps.setInt(7, requestModel.getOffset());

        ServiceLogger.LOGGER.info(ps.toString());

        ResultSet rs = ps.executeQuery();

        ArrayList<GymBuddy> gymBuddies = new ArrayList<>();
        SearchGymbuddyReponseModel reponseModel;

        while(rs.next())
        {
            String email = rs.getString("email");
            String gym = rs.getString("gym");
            String location = rs.getString("location");
            String fitness = rs.getString("fitness");
            String biography = rs.getString("biography");
            String music = rs.getString("music");

            GymBuddy gymBuddy = new GymBuddy(email,location,gym,biography,music,fitness);
            //make a list of match users
            gymBuddies.add(gymBuddy);
        }

        if(gymBuddies.size() != 0)
        {
            reponseModel = new SearchGymbuddyReponseModel("Found users with search parameter", 210,
                    GymBuddy.arrayListTwoArray(gymBuddies));
            return Response.status(Response.Status.OK).entity(reponseModel).build();

        }
        else
        {
            reponseModel = new SearchGymbuddyReponseModel("No users found with search parameter", 211);
            return Response.status(Response.Status.OK).entity(reponseModel).build();
        }


    }
}
