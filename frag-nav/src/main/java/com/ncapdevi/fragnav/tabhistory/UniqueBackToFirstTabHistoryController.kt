package com.ncapdevi.fragnav.tabhistory

import com.ncapdevi.fragnav.FragNavPopController
import com.ncapdevi.fragnav.FragNavSwitchController
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Martin Filípek on 24.06.2019.
 * AppsDevTeam
 * martinf@appsdevteam.com
 */

class UniqueBackToFirstTabHistoryController(fragNavPopController: FragNavPopController,
                                            fragNavSwitchController: FragNavSwitchController) : CollectionFragNavTabHistoryController(fragNavPopController, fragNavSwitchController) {
    private val tabHistory = Stack<Int>()

    override val collectionSize: Int
        get() = tabHistory.size

    override val andRemoveIndex: Int
        get() {
            tabHistory.pop()
            return tabHistory.pop()
        }

    override var history: ArrayList<Int>
        get() = ArrayList(tabHistory)
        set(history) {
            tabHistory.clear()
            tabHistory.addAll(history)
        }

    override fun switchTab(index: Int) {
        if (!tabHistory.empty()) {
            if (tabHistory.firstElement() != index) {
                tabHistory.remove(index)
            } else {
                val position = tabHistory.lastIndexOf(index)

                if (position > 0) {
                    tabHistory.removeAt(position)
                }
            }
        }

        if(tabHistory.empty() || tabHistory.lastElement() != index)
            tabHistory.push(index)
    }
}