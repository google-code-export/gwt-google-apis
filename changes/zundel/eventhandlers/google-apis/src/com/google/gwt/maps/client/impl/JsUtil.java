/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.InfoWindowContent.InfoWindowTab;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;

import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.FieldName;

import java.util.Arrays;

/**
 * This class provides utilities to aid in GWT-JavaScript interoperability.
 * 
 * This is not part of the public API and may change or be removed from future
 * versions.
 * 
 * TODO: Shouldn't this be an implementation class?
 * 
 * TODO: I'd like to see the functionality provided by this class incorporated
 * into JSIO (gwt-api-interop).
 */
public final class JsUtil {

  static interface ListGenerator extends JSFlyweightWrapper {
    @FieldName("valueOf")
    JSList<InfoWindowTab> asInfoWindowTabList(JavaScriptObject jso);

    @FieldName("valueOf")
    JSList<Integer> asIntegerList(JavaScriptObject jso);

    @FieldName("valueOf")
    JSList<LatLng> asLatLngList(JavaScriptObject jso);

    @FieldName("valueOf")
    JSList<Marker> asMarkerList(JavaScriptObject jso);

    @FieldName("valueOf")
    JSList<Point> asPointList(JavaScriptObject jso);

    @FieldName("valueOf")
    JSList<TileLayer> asTileLayerList(JavaScriptObject jso);

    @FieldName("valueOf")
    JSList<Waypoint> asWaypointList(JavaScriptObject jso);

    @Constructor("Array")
    JavaScriptObject newArray();
  }

  private static final ListGenerator lists = GWT.create(ListGenerator.class);

  public static native JavaScriptObject asJavaScriptObject(LatLng latlng) /*-{
    return latlng.@com.google.gwt.maps.client.geom.LatLng::jsoPeer;
  }-*/;

  public static native JavaScriptObject asJavaScriptObject(Placemark placemark) /*-{
    return placemark.@com.google.gwt.maps.client.geocode.Placemark::jsoPeer;
  }-*/;

  public static native JavaScriptObject asJavaScriptObject(String str) /*-{
    return new String(str);
  }-*/;

  public static void toArray(JSList<Integer> list, int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = list.get(i).intValue();
    }
  }

  public static void toArray(JSList<?> list, Object[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = list.get(i);
    }
  }

  public static JSList<InfoWindowTab> toJsList(InfoWindowTab[] array) {
    JSList<InfoWindowTab> list = lists.asInfoWindowTabList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList<Integer> toJsList(int[] array) {
    JSList<Integer> list = lists.asIntegerList(lists.newArray());
    for (int i = 0; i < array.length; i++) {
      list.add(new Integer(array[i]));
    }
    return list;
  }

  public static JSList<LatLng> toJsList(LatLng[] array) {
    JSList<LatLng> list = lists.asLatLngList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList<Marker> toJsList(Marker[] array) {
    JSList<Marker> list = lists.asMarkerList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList<Point> toJsList(Point[] array) {
    JSList<Point> list = lists.asPointList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList<TileLayer> toJsList(TileLayer[] array) {
    JSList<TileLayer> list = lists.asTileLayerList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList<Waypoint> toJsList(Waypoint[] array) {
    JSList<Waypoint> list = lists.asWaypointList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  private JsUtil() {
  }

}