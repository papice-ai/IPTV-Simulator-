package com.papice.iptvsimulator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papice.iptvsimulator.data.db.entity.ChannelCategoryEntity
import com.papice.iptvsimulator.data.repository.ChannelCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelCategoryViewModel @Inject constructor(
    private val categoryRepository: ChannelCategoryRepository
) : ViewModel() {
    
    private val _categories = MutableStateFlow<List<ChannelCategoryEntity>>(emptyList())
    val categories: StateFlow<List<ChannelCategoryEntity>> = _categories.asStateFlow()
    
    private val _countryCategoriesMap = MutableStateFlow<Map<Long, List<ChannelCategoryEntity>>>(emptyMap())
    val countryCategoriesMap: StateFlow<Map<Long, List<ChannelCategoryEntity>>> = _countryCategoriesMap.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    init {
        loadAllCategories()
    }
    
    private fun loadAllCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                categoryRepository.getAllCategoriesFlow().collect { categories ->
                    _categories.value = categories
                    _error.value = null
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadCategoriesByCountry(countryId: Long) {
        viewModelScope.launch {
            try {
                categoryRepository.getCategoriesByCountryFlow(countryId).collect { categories ->
                    val currentMap = _countryCategoriesMap.value.toMutableMap()
                    currentMap[countryId] = categories
                    _countryCategoriesMap.value = currentMap
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    
    fun addCategory(category: ChannelCategoryEntity) {
        viewModelScope.launch {
            try {
                categoryRepository.addCategory(category)
                loadAllCategories()
            } catch (e: Exception) {
                _error.value = "Failed to add category: ${e.message}"
            }
        }
    }
    
    fun deleteCategory(category: ChannelCategoryEntity) {
        viewModelScope.launch {
            try {
                categoryRepository.deleteCategory(category)
                loadAllCategories()
            } catch (e: Exception) {
                _error.value = "Failed to delete category: ${e.message}"
            }
        }
    }
    
    fun searchCategories(query: String) {
        viewModelScope.launch {
            try {
                categoryRepository.searchCategories(query).collect { results ->
                    _categories.value = results
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    
    fun clearError() {
        _error.value = null
    }
}
