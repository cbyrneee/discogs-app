package dev.cbyrne.discogs.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInformationModel(
    @SerialName("profile")
    val bio: String,
    @SerialName("wantlist_url")
    val wantlistUrl: String,
    val rank: Double,
    @SerialName("num_pending")
    val amountPending: Int,
    val id: Int,
    @SerialName("num_for_sale")
    val amountForSale: Int,
    @SerialName("home_page")
    val homepage: String,
    val location: String,
    @SerialName("collection_folders_url")
    val collectionFoldersUrl: String,
    val username: String,
    @SerialName("collection_fields_url")
    val collectionFieldsUrl: String,
    @SerialName("releases_contributed")
    val releasesContributed: Int,
    val registered: String,
    @SerialName("rating_avg")
    val averageRating: Double,
    @SerialName("num_collection")
    val amountInCollection: Int? = null,
    @SerialName("releases_rated")
    val releasesRated: Int,
    @SerialName("num_lists")
    val amountOfLists: Int? = null,
    val name: String,
    @SerialName("num_wantlist")
    val amountInWantlist: Int? = null,
    @SerialName("inventory_url")
    val inventoryUrl: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("banner_url")
    val bannerUrl: String,
    val uri: String,
    @SerialName("resource_url")
    val resourceUrl: String,
    @SerialName("buyer_rating")
    val buyerRating: Double,
    @SerialName("buyer_rating_stars")
    val buyerStars: Double,
    @SerialName("buyer_num_ratings")
    val amountOfBuyerRatings: Int,
    @SerialName("seller_rating")
    val sellerRating: Double,
    @SerialName("seller_rating_stars")
    val sellerStars: Double,
    @SerialName("seller_num_ratings")
    val amountOfSellerRatings: Int,
    @SerialName("curr_abbr")
    var currency: String,
    val email: String? = null,
)
