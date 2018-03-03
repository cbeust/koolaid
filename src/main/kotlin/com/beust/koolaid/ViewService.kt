package com.beust.koolaid

import com.google.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v0/views")
class ViewService @Inject constructor(val dao: ViewsDao) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun retrieveViewCount(): ViewCount = ViewCount(dao.getViewCountAndIncrement())
}
