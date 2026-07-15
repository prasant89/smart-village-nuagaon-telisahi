package com.nuagaon.telisahi.ui.features.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuagaon.telisahi.domain.model.DevelopmentProject
import com.nuagaon.telisahi.domain.model.ProjectCategory
import com.nuagaon.telisahi.domain.model.VillageMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CommunityState())
    val uiState: StateFlow<CommunityState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: CommunityEvent) {
        when (event) {
            is CommunityEvent.LoadData -> loadData()
            is CommunityEvent.OnTabSelected -> {
                _uiState.update { it.copy(selectedTab = event.index) }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000)
            
            val members = listOf(
                VillageMember("1", "Prasant Pradhan", "Lead Developer", "Telisahi", "9876543210"),
                VillageMember("2", "Ramesh Kumar", "Village Head", "Main Nuagaon", "9876543211"),
                VillageMember("3", "Sita Devi", "Health Worker", "Telisahi", "9876543212")
            )

            val projects = listOf(
                DevelopmentProject(
                    "1", "Smart School Wing", "Building 4 new digital classrooms with high-speed internet.",
                    0.75f, ProjectCategory.EDUCATION, "₹15,00,000"
                ),
                DevelopmentProject(
                    "2", "Clean Water Phase 2", "Installing solar-powered water filtration in Telisahi.",
                    0.40f, ProjectCategory.WATER, "₹8,50,000"
                ),
                DevelopmentProject(
                    "3", "Panchayat Road Repair", "Repairing the main approach road to Nuagaon village.",
                    1.0f, ProjectCategory.INFRASTRUCTURE, "₹12,00,000"
                )
            )

            _uiState.update { 
                it.copy(
                    isLoading = false,
                    members = members,
                    projects = projects
                ) 
            }
        }
    }
}
