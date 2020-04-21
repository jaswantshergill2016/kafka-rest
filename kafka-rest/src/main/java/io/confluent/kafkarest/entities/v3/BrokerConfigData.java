/*
 * Copyright 2020 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafkarest.entities.v3;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import java.util.StringJoiner;
import javax.annotation.Nullable;

/**
 * A (Broker) config resource type.
 */
public final class BrokerConfigData {

  public static final String ELEMENT_TYPE = "config";

  private final String id;

  private final ResourceLink links;

  private final Attributes attributes;

  public BrokerConfigData(
      String id,
      ResourceLink links,
      String clusterId,
      int brokerId,
      String name,
      @Nullable String value,
      boolean isDefault,
      boolean isReadOnly,
      boolean isSensitive) {
    this.id = id;
    this.links = links;
    attributes =
        new Attributes(clusterId, brokerId, name, value, isDefault, isReadOnly, isSensitive);
  }

  @JsonProperty("type")
  public String getType() {
    return "KafkaBrokerConfig";
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("links")
  public ResourceLink getLinks() {
    return links;
  }

  @JsonProperty("attributes")
  public Attributes getAttributes() {
    return attributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BrokerConfigData that = (BrokerConfigData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(links, that.links)
        && Objects.equals(attributes, that.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, links, attributes);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BrokerConfigData.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("links=" + links)
        .add("attributes=" + attributes)
        .toString();
  }

  public static final class Attributes {

    private final String clusterId;

    private final int brokerId;

    private final String name;

    @Nullable
    private final String value;

    private final boolean isDefault;

    private final boolean isReadOnly;

    private final boolean isSensitive;

    public Attributes(
        String clusterId,
        int brokerId,
        String name,
        @Nullable String value,
        boolean isDefault,
        boolean isReadOnly,
        boolean isSensitive) {
      this.clusterId = Objects.requireNonNull(clusterId);
      this.brokerId = Objects.requireNonNull(brokerId);
      this.name = Objects.requireNonNull(name);
      this.value = value;
      this.isDefault = isDefault;
      this.isReadOnly = isReadOnly;
      this.isSensitive = isSensitive;
    }

    @JsonProperty("cluster_id")
    public String getClusterId() {
      return clusterId;
    }

    @JsonProperty("broker_id")
    public int getBrokerId() {
      return brokerId;
    }

    @JsonProperty("name")
    public String getName() {
      return name;
    }

    @JsonProperty("value")
    @Nullable
    public String getValue() {
      return value;
    }

    @JsonProperty("is_default")
    public boolean isDefault() {
      return isDefault;
    }

    @JsonProperty("is_read_only")
    public boolean isReadOnly() {
      return isReadOnly;
    }

    @JsonProperty("is_sensitive")
    public boolean isSensitive() {
      return isSensitive;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      BrokerConfigData.Attributes that = (BrokerConfigData.Attributes) o;
      return isDefault == that.isDefault
          && isReadOnly == that.isReadOnly
          && isSensitive == that.isSensitive
          && Objects.equals(clusterId, that.clusterId)
          && Objects.equals(brokerId, that.brokerId)
          && Objects.equals(name, that.name)
          && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(clusterId, brokerId, name, value, isDefault, isReadOnly, isSensitive);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", BrokerConfigData.Attributes.class.getSimpleName() + "[", "]")
          .add("clusterId='" + clusterId + "'")
          .add("brokerId='" + brokerId + "'")
          .add("name='" + name + "'")
          .add("value='" + value + "'")
          .add("isDefault=" + isDefault)
          .add("isReadOnly=" + isReadOnly)
          .add("isSensitive=" + isSensitive)
          .toString();
    }
  }
}
