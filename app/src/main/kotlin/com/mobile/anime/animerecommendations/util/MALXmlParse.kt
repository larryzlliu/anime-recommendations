package com.mobile.anime.animerecommendations.util

import org.xmlpull.v1.XmlPullParser
import android.util.Xml
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream


/**
 * Created by larryliu on 2017-09-17.
 */
class MALXmlParse {

    private val ns: String? = null

    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(`in`: InputStream): List<Anime> {
        try {
            val parser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(`in`, null)
            parser.nextTag()
            return readFeed(parser)
        } finally {
            `in`.close()
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readFeed(parser: XmlPullParser): List<Anime> {
        val animes : MutableList<Anime> = ArrayList()

        parser.require(XmlPullParser.START_TAG, ns, "feed")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            // Starts by looking for the entry tag
            if (name == "anime") {
                animes.add(readEntry(parser))
            } else {
                skip(parser)
            }
        }
        return animes
    }

    class Anime constructor(val id: String)

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readEntry(parser: XmlPullParser): Anime {
        parser.require(XmlPullParser.START_TAG, ns, "anime")
        var id: String? = null
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            if (name == "series_animedb_id") {
                id = readId(parser)
            } else {
                skip(parser)
            }
        }
        return Anime(id!!)
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readId(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "series_animedb_id")
        val id = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "series_animedb_id")
        return id
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}