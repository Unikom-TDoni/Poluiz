package com.lnco.poluiz.feature.presentation.atomic.organism.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.lnco.poluiz.R
import com.lnco.poluiz.feature.presentation.theme.PoluizTheme
import com.lnco.poluiz.feature.presentation.theme.dimens

@Composable
fun CongratulationOrganism(
    id: String,
    score: Int
) =
    Column(
        modifier = Modifier.fillMaxWidth().layoutId(id),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_medal),
            modifier = Modifier.height(MaterialTheme.dimens.congratulationImage),
            contentDescription = "congratulation_image"
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.normal100))
        Text("Congratulation", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.mini80))
        Text("Your Score Is $score", style = MaterialTheme.typography.h5)
    }

@Preview(showBackground = true)
@Composable
private fun Preview() =
    PoluizTheme {
        CongratulationOrganism("", 100)
    }