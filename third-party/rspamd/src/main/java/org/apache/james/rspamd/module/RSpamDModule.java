/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.rspamd.module;

import org.apache.james.rspamd.route.FeedMessageRoute;
import org.apache.james.rspamd.task.FeedHamToRSpamDTaskAdditionalInformationDTO;
import org.apache.james.rspamd.task.FeedSpamToRSpamDTaskAdditionalInformationDTO;
import org.apache.james.server.task.json.dto.AdditionalInformationDTO;
import org.apache.james.server.task.json.dto.AdditionalInformationDTOModule;
import org.apache.james.task.TaskExecutionDetails;
import org.apache.james.webadmin.Routes;
import org.apache.james.webadmin.dto.DTOModuleInjections;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;

public class RSpamDModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<Routes> routesMultiBinder = Multibinder.newSetBinder(binder(), Routes.class);
        routesMultiBinder.addBinding().to(FeedMessageRoute.class);
    }

    @ProvidesIntoSet
    public AdditionalInformationDTOModule<? extends TaskExecutionDetails.AdditionalInformation, ? extends AdditionalInformationDTO> feedSpamAdditionalInformation() {
        return FeedSpamToRSpamDTaskAdditionalInformationDTO.SERIALIZATION_MODULE;
    }

    @Named(DTOModuleInjections.WEBADMIN_DTO)
    @ProvidesIntoSet
    public AdditionalInformationDTOModule<? extends TaskExecutionDetails.AdditionalInformation, ? extends AdditionalInformationDTO> webAdminFeedSpamAdditionalInformation() {
        return FeedSpamToRSpamDTaskAdditionalInformationDTO.SERIALIZATION_MODULE;
    }

    @ProvidesIntoSet
    public AdditionalInformationDTOModule<? extends TaskExecutionDetails.AdditionalInformation, ? extends AdditionalInformationDTO> feedHamAdditionalInformation() {
        return FeedHamToRSpamDTaskAdditionalInformationDTO.SERIALIZATION_MODULE;
    }

    @Named(DTOModuleInjections.WEBADMIN_DTO)
    @ProvidesIntoSet
    public AdditionalInformationDTOModule<? extends TaskExecutionDetails.AdditionalInformation, ? extends AdditionalInformationDTO> webAdminFeedHamAdditionalInformation() {
        return FeedHamToRSpamDTaskAdditionalInformationDTO.SERIALIZATION_MODULE;
    }
}
