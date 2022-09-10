package dev.cbyrne.discogs.feature.discover.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.cbyrne.discogs.api.model.user.UserInformationModel

@Composable
fun ProfileCard(information: UserInformationModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = information.avatarUrl,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = information.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(5.dp))
            )

            Column {
                Text(text = information.name)
                Text(
                    text = information.username,
                    modifier = Modifier.alpha(0.75f)
                )
            }
        }
    }
}