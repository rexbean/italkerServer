package net.rex.italker.web.push;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.rex.italker.web.push.provider.GsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import net.rex.italker.web.push.service.AccountService;

import java.util.logging.Logger;

/**
 * Created by Rexbean on 6/13/2018.
 */
public class Application extends ResourceConfig{
    public Application(){
        packages(AccountService.class.getPackage().getName());

//        register(JacksonJsonProvider.class);
        // boolean in jackson will have prefix is
        // collections in jackson will output all
        // return model is a user card

        register(GsonProvider.class);

        register(Logger.class);
    }
}
