package nl.dierenasiel.opdracht;


import nl.dierenasiel.opdracht.endpoint.DierEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class DierenAsielApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(DierenAsielAPI.class);
        set.add(DierEndpoint.class);
        return set;
    }

}
