package com.radiogarden.core.data.sample

import com.radiogarden.core.domain.model.RadioStation
import com.radiogarden.core.domain.model.RadioLocation

object SampleRadioData {
    
    val sampleStations = listOf(
        RadioStation(
            id = "bbc_radio_1",
            name = "BBC Radio 1",
            country = "United Kingdom",
            city = "London",
            streamUrl = "http://stream.live.vc.bbcmedia.co.uk/bbc_radio_one",
            website = "https://www.bbc.co.uk/radio1",
            description = "The UK's number one hit music station",
            genre = "Pop",
            language = "English",
            latitude = 51.5074,
            longitude = -0.1278,
            bitrate = 128,
            codec = "MP3"
        ),
        RadioStation(
            id = "radio_france_inter",
            name = "France Inter",
            country = "France",
            city = "Paris",
            streamUrl = "http://direct.franceinter.fr/live/franceinter-midfi.mp3",
            website = "https://www.franceinter.fr",
            description = "La radio de service public française",
            genre = "Talk",
            language = "French",
            latitude = 48.8566,
            longitude = 2.3522,
            bitrate = 128,
            codec = "MP3"
        ),
        RadioStation(
            id = "radio_deutschlandfunk",
            name = "Deutschlandfunk",
            country = "Germany",
            city = "Cologne",
            streamUrl = "https://st01.sslstream.dlf.de/dlf/01/128/mp3/stream.mp3",
            website = "https://www.deutschlandfunk.de",
            description = "Deutschlands führende Nachrichten- und Informationswelle",
            genre = "News",
            language = "German",
            latitude = 50.9375,
            longitude = 6.9603,
            bitrate = 128,
            codec = "MP3"
        ),
        RadioStation(
            id = "radio_nhk_world",
            name = "NHK World Radio Japan",
            country = "Japan",
            city = "Tokyo",
            streamUrl = "https://nhkworld.webcdn.stream.ne.jp/www11/nhkworld-tv/global/2003458/live_hi_s.m3u8",
            website = "https://www3.nhk.or.jp/nhkworld/",
            description = "Japan's international broadcasting service",
            genre = "News",
            language = "English",
            latitude = 35.6762,
            longitude = 139.6503,
            bitrate = 128,
            codec = "AAC"
        ),
        RadioStation(
            id = "radio_abc_classic",
            name = "ABC Classic",
            country = "Australia",
            city = "Sydney",
            streamUrl = "https://live-radio01.mediahubaustralia.com/2FCW/mp3/",
            website = "https://www.abc.net.au/classic/",
            description = "Australia's classical music station",
            genre = "Classical",
            language = "English",
            latitude = -33.8688,
            longitude = 151.2093,
            bitrate = 128,
            codec = "MP3"
        ),
        RadioStation(
            id = "radio_rtp_antena1",
            name = "RTP Antena 1",
            country = "Portugal",
            city = "Lisbon",
            streamUrl = "https://streaming.rtp.pt/antena180a.mp3",
            website = "https://www.rtp.pt/antena1/",
            description = "A rádio mais ouvida em Portugal",
            genre = "Pop",
            language = "Portuguese",
            latitude = 38.7223,
            longitude = -9.1393,
            bitrate = 128,
            codec = "MP3"
        )
    )
    
    val sampleLocations = listOf(
        RadioLocation(
            id = "london_uk",
            name = "London",
            country = "United Kingdom",
            latitude = 51.5074,
            longitude = -0.1278,
            stationCount = 1
        ),
        RadioLocation(
            id = "paris_fr",
            name = "Paris",
            country = "France",
            latitude = 48.8566,
            longitude = 2.3522,
            stationCount = 1
        ),
        RadioLocation(
            id = "cologne_de",
            name = "Cologne",
            country = "Germany",
            latitude = 50.9375,
            longitude = 6.9603,
            stationCount = 1
        ),
        RadioLocation(
            id = "tokyo_jp",
            name = "Tokyo",
            country = "Japan",
            latitude = 35.6762,
            longitude = 139.6503,
            stationCount = 1
        ),
        RadioLocation(
            id = "sydney_au",
            name = "Sydney",
            country = "Australia",
            latitude = -33.8688,
            longitude = 151.2093,
            stationCount = 1
        ),
        RadioLocation(
            id = "lisbon_pt",
            name = "Lisbon",
            country = "Portugal",
            latitude = 38.7223,
            longitude = -9.1393,
            stationCount = 1
        )
    )
}
