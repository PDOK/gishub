package nl.kadaster.pdok.boot.controller

object RouteConstants {

    const val BASE = "/api"
    const val VERSION = 0
    const val MOUNT = "$BASE/v$VERSION"

    const val PRIVATE = "$MOUNT/restricted"

}