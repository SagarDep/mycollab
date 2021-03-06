/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.domain.criteria

import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.arguments.StringSearchField

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectGenericItemSearchCriteria : SearchCriteria() {
    var prjKeys: SetSearchField<Int>? = null
    var txtValue: StringSearchField? = null
    var createdUsers: SetSearchField<String>? = null
    var types: SetSearchField<String>? = null
    var monitorProjectIds: SetSearchField<Int>? = null
    var tagNames: SetSearchField<String>? = null
}