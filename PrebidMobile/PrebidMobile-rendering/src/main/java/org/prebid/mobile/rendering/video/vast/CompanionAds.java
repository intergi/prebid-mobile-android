/*
 *    Copyright 2018-2021 Prebid.org, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.prebid.mobile.rendering.video.vast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class CompanionAds extends VASTParserBase
{
    private final static String VAST_COMPANIONADS = "CompanionAds";
    private final static String VAST_COMPANION = "Companion";

    private ArrayList<Companion> mCompanionAds;

	public CompanionAds(XmlPullParser p) throws XmlPullParserException, IOException
	{

        mCompanionAds = new ArrayList<>();

		p.require(XmlPullParser.START_TAG, null, VAST_COMPANIONADS);

		while (p.next() != XmlPullParser.END_TAG)
		{
			if (p.getEventType() != XmlPullParser.START_TAG)
			{
				continue;
			}
			String name = p.getName();
			if (name != null && name.equals(VAST_COMPANION))
			{
				p.require(XmlPullParser.START_TAG, null, VAST_COMPANION);
                mCompanionAds.add(new Companion(p));
				p.require(XmlPullParser.END_TAG, null, VAST_COMPANION);
			}
			else
			{
				skip(p);
			}
		}

	}

    public ArrayList<Companion> getCompanionAds() {
        return mCompanionAds;
    }
}
