package com.example.myapplication.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.data.data_classes.characters.Results
import com.example.myapplication.data.data_classes.episode.Episode
import com.example.myapplication.presentation.CharacterDetailsViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    val smallFont = 15.sp
    val mediumFont = 20.sp
    val titleFont = 20.sp
    private var info: Results? = null
    private val viewModel: CharacterDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { info = it.getParcelable(MainFragment.RESULTS_KEY) }
        Log.d("MyLog" , info.toString())
        return ComposeView(requireContext()).apply {
            setContent {
                info?.let {
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        GlideImage(
                            imageModel = { info!!.image.toString() } ,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Column(modifier = Modifier.padding(PaddingValues(30.dp , 15.dp))) {

                            Text(
                                text = "${info!!.name.toString()}" ,
                                fontSize = titleFont ,
                                fontWeight = FontWeight.Bold
                            )
                            infoCard(str1 = "status:" , str2 = "${info!!.status.toString()}")
                            infoCard(
                                str1 = "species and gender:" ,
                                str2 = "${info!!.species.toString()}(${info!!.gender.toString()})"
                            )
                            infoCard(
                                str1 = "origin:" ,
                                str2 = "${info!!.origin?.name.toString()}"
                            )
                            infoCard(
                                str1 = "location:" ,
                                str2 = "${info!!.location?.name.toString()}"
                            )
                            Text(
                                text = "Episodes:" ,
                                fontSize = titleFont ,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        seriesList(episodesUrls = info!!.episode)
                    }
                }
            }
        }
    }

    @Composable
    fun infoCard(str1: String , str2: String) {
        Column(
            modifier = Modifier
                .padding(PaddingValues(0.dp , 10.dp))
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = str1 , fontSize = smallFont)
            Text(text = str2 , fontSize = mediumFont)
        }
    }

    @Preview
    @Composable
    fun seriesListPreview() {
        val series = arrayListOf<String>()
        for (i in 1..10) {
            series.add("episode number $i")
        }
        seriesList(episodesUrls = series)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun seriesList(episodesUrls: ArrayList<String>) {
        val episodes = viewModel.episodes.collectAsState(initial = emptyList<Episode>())
        viewModel.load(episodesUrls)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in episodes.value.indices) {
                Card(
                    modifier = Modifier
                        .padding(PaddingValues(0.dp , 0.dp , 30.dp , 0.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    var date = LocalDate.parse(episodes.value[i].created?.take(10))
                    val formatter = DateTimeFormatter.ofPattern("MMMM,dd,yyyy")
                    val formatted = date.format(formatter)
                    Text(
                        text = "${episodes.value[i].episode}" ,
                        fontSize = smallFont ,
                        textAlign = TextAlign.Right
                    )
                    Text(text = "${episodes.value[i].name}" , fontSize = mediumFont)
                    Text(text = formatted , fontSize = smallFont)
                }
            }
        }
    }


}
