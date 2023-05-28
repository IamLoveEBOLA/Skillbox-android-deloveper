package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.myapplication.data.data_classes.locations.ResultsLocation
import com.example.myapplication.databinding.FragmentLocationsBinding
import com.example.myapplication.presentation.LocationsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow



@AndroidEntryPoint
class LocationsFragment : Fragment() {
    private val viewModel: LocationsFragmentViewModel by viewModels()
    lateinit var binding: FragmentLocationsBinding



    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                LocationsList(locations = viewModel.pagedLocations)
            }
        }
    }

    @Composable
    fun LocationsList(locations: Flow<PagingData<ResultsLocation>>) {
        val lazyLocationsItems: LazyPagingItems<ResultsLocation> =
            locations.collectAsLazyPagingItems()
        LazyColumn {
            items(lazyLocationsItems) { item ->
                if (item != null) {
                    LocationItem(location = item)
                }
            }
        }
        lazyLocationsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingView(modifier = Modifier.fillMaxSize())
                }
                loadState.append is LoadState.Loading -> {
                    LoadingItem()
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyLocationsItems.loadState.refresh as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!! ,
                        modifier = Modifier.fillMaxSize() ,
                        onClickRetry = { retry() }
                    )

                }
                loadState.append is LoadState.Error -> {
                    val e = lazyLocationsItems.loadState.append as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!! ,
                        onClickRetry = { retry() }
                    )

                }
            }
        }
    }
    @Composable
    fun LocationItem(location: ResultsLocation) {
        Column {
            Card(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight()) {
                Text(text = "name: ${location.name}")
                Text(text = "type: ${location.type}")
                Text(text = "dimension: ${location.dimension}")
            }
        }
    }

    @Composable
    fun LoadingView(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier ,
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun LoadingItem() {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

    @Composable
    fun ErrorItem(
        message: String ,
        modifier: Modifier = Modifier ,
        onClickRetry: () -> Unit
    ) {
        Row(
            modifier = modifier.padding(16.dp) ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message ,
                maxLines = 1 ,
                modifier = Modifier.weight(1f) ,
                style = MaterialTheme.typography.bodyMedium ,
                color = Color.Red
            )
            OutlinedButton(onClick = onClickRetry) {
                Text(text = "Try again")
            }
        }
    }
}
