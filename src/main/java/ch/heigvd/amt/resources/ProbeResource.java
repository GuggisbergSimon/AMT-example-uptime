package ch.heigvd.amt.resources;

import ch.heigvd.amt.services.ProbeService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ProbeResource {

    @Inject
    Template indexPage;

    @Inject
    Template registerPage;

    @Inject
    Template probesPage;

    @Inject
    ProbeService probeService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return indexPage.instance();
    }

    @GET
    @Path("/register")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance register() {
        return registerPage.instance();
    }

    @GET
    @Path("/probes")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance probes() {
        var probeList = probeService.listProbes();
        return probesPage.data("probeList", probeList);
    }

    @POST
    @Path("/probes")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance registerProbes(@FormParam("url") String url) {
        probeService.getOrCreateProbe(url);
        return probesPage.data("probeList", probeService.listProbes());
    }

    @Inject
    Template statusPage;

    @GET
    @Path("/probes/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance probes(@PathParam("id") long id) {
        var probe = probeService.getProbeById(id);
        var statusList = probeService.getProbeStatusListDesc(probe);
        return statusPage
                .data("probe", probe)
                .data("lastStatus", statusList.getFirst())
                .data("statusList", statusList);
    }
}
