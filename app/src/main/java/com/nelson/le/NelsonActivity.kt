package com.nelson.le
//Nelson Le - 301064831

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NelsonActivity : AppCompatActivity(), LandmarkAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LandmarkAdapter

    private val landmarkMap: MutableMap<String, List<Landmark>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nelson_activity)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize landmarkMap with landmarks
        initializeLandmarks()

        // Get all landmark types
        val landmarkTypes = landmarkMap.keys.toList()

        // Initialize the adapter with the list of landmark types
        adapter = LandmarkAdapter(ArrayList(landmarkTypes), this)
        recyclerView.adapter = adapter


    }

    private fun initializeLandmarks() {
        val historicalSites = listOf(
            Landmark("Edinburgh Castle", "Castlehill, Edinburgh, EH1 2NG", 55.9486, -3.1999, "Old Building", "https://happytowander.com/wp-content/uploads/Edinburgh-Castle-Edinburgh-Scotland-05955.jpg"),
            Landmark("Palace of Holyroodhouse", "Canongate, Edinburgh, EH8 8DX", 55.9523, -3.1712, "Old Building", "https://cdn-imgix.headout.com/media/images/d8d5b6a8728ad1981ea945c7388dfd25-Palace%20of%20Holyroodhouse.jpg"),
            Landmark("St. Giles' Cathedral", "High St, Edinburgh, EH1 1RE", 55.9516, -3.1870, "Old Building", "https://www.secret-scotland.com/datafiles/uploaded/cmsRefImage/popularPlaces/additional/main/main_191_StGiles.jpg"),
            Landmark("Scott Monument", "Princes St, Edinburgh, EH2 2EJ", 55.9523, -3.1933, "Old Building", "https://i.redd.it/viuyw52jv6x21.jpg"),
            Landmark("Edinburgh Old Town", "Various Locations", 55.9500, -3.1880, "Old Building", "https://media.timeout.com/images/105956098/750/422/image.jpg"),
        )

        val museums = listOf(
            Landmark("National Museum of Scotland", "Chambers St, Edinburgh, EH1 1JF", 55.9479, -3.1897, "Museum", "https://media.cntraveler.com/photos/5c2cfeb8a6a145617b7c781c/16:9/w_2560,c_limit/National-Museum-of-Scotland_Grand-Gallery-at-the-National-Museum-of-Scotland.-Copyright-National-Museums-Scotland.jpg"),
            Landmark("Scottish National Gallery", "The Mound, Edinburgh, EH2 2EL", 55.9510, -3.1973, "Museum", "https://sothebys-com.brightspotcdn.com/dims4/default/d5e514a/2147483647/strip/true/crop/2316x1310+72+0/resize/1156x654!/quality/90/?url=http%3A%2F%2Fsothebys-brightspot.s3.amazonaws.com%2Fdotcom%2Fce%2F14%2Fc43f27ec0457208b13c7059e7862%2Fimages-for-scottish-national-gallery-of-modern-art-modern-two-interiors-national-galleries-of-scotland-photography-paul-raeburn-2.jpg"),
            Landmark("Museum of Edinburgh", "142 Canongate, Edinburgh, EH8 8DD", 55.9513, -3.1814, "Museum", "https://d3d00swyhr67nd.cloudfront.net/w800h800/collection/PUBSCULPT/EDI/EDI_CITY_location_image_7.jpg"),
            Landmark("Edinburgh Castle Museum", "Castlehill, Edinburgh, EH1 2NG", 55.9486, -3.1999, "Museum", "https://www.edinburghcity.org.uk/wp-content/uploads/2020/04/national-war-museum-edinburgh-1.jpg"),
            Landmark("Surgeons' Hall Museums", "Nicolson St, Edinburgh, EH8 9DW", 55.9477, -3.1847, "Museum", "https://2f1a7f9478.visitscotland.net/wsimgs/The_Wohl_Pathology_Museum_2096467461.JPG"),
        )

        val sportsArenas = listOf(
            Landmark("BT Murrayfield Stadium", "Roseburn St, Edinburgh, EH12 5PJ", 55.9425, -3.2406, "Stadium", "https://scottishrugbyhospitality.org/wp-content/uploads/2020/02/19265602-scaled-1.jpg"),
            Landmark("Easter Road Stadium", "12 Albion Pl, Edinburgh, EH7 5QG", 55.9612, -3.1651, "Stadium", "https://d3tepru76oevpi.cloudfront.net/production/_ctaImageFull/124956/ER2.jpg"),
            Landmark("Tynecastle Park", "Gorgie Rd, Edinburgh, EH11 2NL", 55.9382, -3.2323, "Stadium", "https://upload.wikimedia.org/wikipedia/commons/9/99/Tynecastle_Park%2C_January_2018.jpg"),
            Landmark("Royal Commonwealth Pool", "21 Dalkeith Rd, Edinburgh, EH16 5BB", 55.9376, -3.1693, "Stadium", "https://www.graham.co.uk/media/projects/Building/Images/Leisure/_800x500_crop_center-center_75_none/CJW_0162x700.jpg"),
            Landmark("Meadowbank Stadium", "London Rd, Edinburgh, EH7 6AE", 55.9615, -3.1651, "Stadium", "https://upload.wikimedia.org/wikipedia/commons/f/f1/Meadowbank_Stadium_stand.jpg"),
        )

        val attractions = listOf(
            Landmark("Edinburgh Zoo", "Corstorphine Rd, Edinburgh, EH12 6TS", 55.9424, -3.2792, "Attraction", "https://www.premierinn.com/content/dam/global/xedinburgh-zoo-500x320.jpg.pagespeed.ic.fau9bZDnOB.jpg"),
            Landmark("Royal Yacht Britannia", "Ocean Dr, Edinburgh, EH6 6JJ", 55.9779, -3.1724, "Attraction", "https://www.thesun.co.uk/wp-content/uploads/2018/05/FY4GWM-CXXPjpg-JS667345747.jpg"),
            Landmark("Arthur's Seat", "Queen's Dr, Edinburgh, EH8 8HG", 55.9441, -3.1617, "Attraction", "https://media.tacdn.com/media/attractions-splice-spp-674x446/0b/27/54/32.jpg"),
            Landmark("Holyrood Park", "Queen's Dr, Edinburgh, EH8 8HG", 55.9534, -3.1616, "Attraction", "https://www.introducingedinburgh.com/f/reino-unido/edimburgo/guia/holyrood-park.jpg"),
            Landmark("The Real Mary King's Close", "2 Warriston's Cl, Edinburgh, EH1 1PG", 55.9496, -3.1909, "Attraction", "https://a.cdn-hotels.com/gdcs/production80/d1981/f2cf1dfd-77d1-4e28-85e8-3d1d653a091b.jpg"),
        )

        landmarkMap["Historical Sites \uD83C\uDFEF️ "] = historicalSites
        landmarkMap["Museums  \uD83C\uDFDB️️"] = museums
        landmarkMap["Sports Arenas \uD83C\uDFDF️"] = sportsArenas
        landmarkMap["Attractions \uD83C\uDFA0"] = attractions
    }

    override fun onItemClick(position: Int) {
        // Get the selected landmark type
        val selectedLandmarkType = adapter.getLandmarkType(position)

        // Launch MapActivity with selected landmark type
        val intent = Intent(this, PlacesListActivity::class.java).apply {
            putExtra("LANDMARK_TYPE", selectedLandmarkType)
        }
        startActivity(intent)
    }

}
