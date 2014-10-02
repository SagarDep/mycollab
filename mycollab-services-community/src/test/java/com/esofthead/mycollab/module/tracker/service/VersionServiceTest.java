/**
 * This file is part of mycollab-services-community.
 *
 * mycollab-services-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.tracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class VersionServiceTest extends ServiceTest {

	@Autowired
	protected VersionService versionService;

	private VersionSearchCriteria getCriteria() {
		VersionSearchCriteria criteria = new VersionSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(1));
		criteria.setProjectId(new NumberSearchField(1));
		return criteria;
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testGetListVersions() {
		List<SimpleVersion> versions = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						getCriteria(), 0, Integer.MAX_VALUE));

		assertThat(versions.size()).isEqualTo(4);
		assertThat(versions).extracting("id", "description", "status",
				"versionname", "numBugs", "numOpenBugs").contains(
				tuple(4, "Version 4.0.0", "Open", "4.0.0", 0, 0),
				tuple(3, "Version 3.0.0", "Closed", "3.0.0", 1, 1),
				tuple(2, "Version 2.0.0", "Closed", "2.0.0", 2, 1),
				tuple(1, "Version 1.0.0", "Open", "1.0.0", 1, 1));
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testTotalCount() {
		List<SimpleVersion> versions = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						getCriteria(), 0, Integer.MAX_VALUE));

		assertThat(versions.size()).isEqualTo(4);
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testFindVersionById() {
		VersionSearchCriteria criteria = new VersionSearchCriteria();
		criteria.setId(new NumberSearchField(1));

		List<SimpleVersion> versions = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		assertThat(versions.size()).isEqualTo(1);
		assertThat(versions).extracting("id", "description", "status",
				"versionname", "numBugs", "numOpenBugs").contains(
				tuple(1, "Version 1.0.0", "Open", "1.0.0", 1, 1));
	}

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testFindByCriteria() {
		VersionSearchCriteria criteria = getCriteria();
		criteria.setId(new NumberSearchField(2));
		criteria.setStatus(new StringSearchField("Closed"));

		List<SimpleVersion> versions = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		assertThat(versions.size()).isEqualTo(1);
		assertThat(versions).extracting("id", "description", "status",
				"versionname", "numBugs", "numOpenBugs").contains(
				tuple(2, "Version 2.0.0", "Closed", "2.0.0", 2, 1));
	}
}