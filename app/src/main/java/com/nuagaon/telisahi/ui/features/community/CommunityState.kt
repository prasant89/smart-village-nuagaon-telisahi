package com.nuagaon.telisahi.ui.features.community

import com.nuagaon.telisahi.domain.model.VillageMember
import com.nuagaon.telisahi.domain.model.DevelopmentProject

data class CommunityState(
    val isLoading: Boolean = false,
    val members: List<VillageMember> = emptyList(),
    val projects: List<DevelopmentProject> = emptyList(),
    val selectedTab: Int = 0
)

sealed class CommunityEvent {
    object LoadData : CommunityEvent()
    data class OnTabSelected(val index: Int) : CommunityEvent()
}
