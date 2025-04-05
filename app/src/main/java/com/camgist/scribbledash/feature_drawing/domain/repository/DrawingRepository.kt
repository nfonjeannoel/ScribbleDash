package com.camgist.scribbledash.feature_drawing.domain.repository

import com.camgist.scribbledash.feature_drawing.domain.model.Path
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing drawing paths
 */
interface DrawingRepository {
    /**
     * Get all paths in the current drawing
     */
    fun getPaths(): Flow<List<Path>>
    
    /**
     * Add a new path to the drawing
     */
    suspend fun addPath(path: Path)
    
    /**
     * Clear all paths from the drawing
     */
    suspend fun clearPaths()
    
    /**
     * Undo the last path added
     */
    suspend fun undoLastPath()
    
    /**
     * Redo the last undone path
     */
    suspend fun redoLastPath()
    
    /**
     * Check if there are any undone paths that can be redone
     */
    fun hasUndonePathsFlow(): Flow<Boolean>
    
    /**
     * Check if there are any paths that can be undone
     */
    fun hasUndoablePathsFlow(): Flow<Boolean>
} 