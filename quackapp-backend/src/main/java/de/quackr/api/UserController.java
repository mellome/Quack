package de.quackr.api;

import de.quackr.api.IOController;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.quackr.persistence.entities.Quack;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;

import de.quackr.persistence.entities.User;
import de.quackr.persistence.entities.User_;

/**
 * @author wangxiaoshi
 * 
 */
@Path("/users")
public class UserController {

    static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
    static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
    static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @EJB
    private IOController ioController;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUser(@PathParam("id") long id) {
        return Response.ok(ioController.getUser(id)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> readAllUsersAsJSON() {
        return ioController.getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User param) {
        try {
            if(!validateInput(param)) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (PatternSyntaxException ex) {
            Response.serverError();
        }

        final User user = new User();
        user.setSignUpTimestamp(new Date(System.currentTimeMillis()));
        user.setLastActiveTimestamp(param.getLastActiveTimestamp());
        user.setUsername(param.getUsername());
        user.setRealName(param.getRealName());
        user.setBirthday(param.getBirthday());
        user.setEmail(param.getEmail());
        user.setPasswordHash(param.getPasswordHash());
        user.setAdmin(param.isAdmin());
        user.setModerator(param.isModerator());

        ioController.createUser(user);

        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") long id) {
        ioController.deleteUser(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateUser(@PathParam("id") long id, User param) {
        try {
            if(!validateInput(param)) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (PatternSyntaxException ex) {
            Response.serverError();
        }

        ioController.updateUser(id, param);
    }

    public static boolean validateInput(User param) {
        return validateUsername(param.getUsername())
            && validateEmail(param.getEmail())
            && validatePassword(param.getPasswordHash());
    }

    public static boolean validateUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    public static boolean validateEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

}
