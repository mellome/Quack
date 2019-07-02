package de.quackr.api;

import de.quackr.persistence.entities.Quack;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author wangxiaoshi
 * 
 */

@Path("/quacks")
public class QuackController {

    //
    static final String TITLE_LENGTH = "^\\W*(?:\\w+\\b\\W*){1,10}$";
    static final String TEXT_LENGTH = "^\\\\W*(?:\\\\w+\\\\b\\\\W*){0,20}$";

    static final String[] BACKGROUND_COLORS = {
        "smoky-black",
        "dark-lavender",
        "mountbatten-pink",
        "bournished-brown",
        "rosy-brown"
    };

    @EJB
    private IOController ioController;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readQuack(@PathParam("id") long id) {
        return Response.ok(ioController.getQuack(id)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Quack> readAllQuacksAsJSON() {
        return ioController.getAllQuacks();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuack(Quack param) {
        try {
            if(!validateInput(param)) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (PatternSyntaxException ex) {
            Response.serverError();
        }

        final Quack quack = new Quack();
        quack.setDate(new Date(System.currentTimeMillis()));
        quack.setAuthor(param.getAuthor());
        quack.setPubliclyVisible(param.isPubliclyVisible());
        quack.setTitle(param.getTitle());
        quack.setText(param.getText());

        quack.setBackgroundColor(BACKGROUND_COLORS[(int)(Math.random()*BACKGROUND_COLORS.length)]);

        ioController.createQuack(quack);

        return Response.ok(quack).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteQuack(@PathParam("id") long id) {
        ioController.deleteQuack(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateQuack(@PathParam("id") long id, Quack param) {
        try {
            if(!validateInput(param)) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (PatternSyntaxException ex) {
            Response.serverError();
        }

        ioController.updateQuack(id, param);
    }

    public static boolean validateInput(Quack param) {
        return validateTitle(param.getTitle())
            && validateText(param.getText())
            && param.getAuthor() != null;
    }

    public static boolean validateTitle(String title) {
        return Pattern.matches(TITLE_LENGTH, title);
    }

    public static boolean validateText(String text) {
        return Pattern.matches(TEXT_LENGTH, text);
    }

}
