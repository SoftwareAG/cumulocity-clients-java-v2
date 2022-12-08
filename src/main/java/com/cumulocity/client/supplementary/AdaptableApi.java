// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.supplementary;

import jakarta.ws.rs.client.WebTarget;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AdaptableApi {

		private final WebTarget rootTarget;
	
		protected AdaptableApi(final WebTarget rootTarget) {
			this.rootTarget = rootTarget;
		}
	
		protected WebTarget getRootTarget() {
			return rootTarget;
		}

	protected String valueOf(final Object s) {
		return s != null ? String.valueOf(s) : null;
	}

	protected <RequestBody> JsonNode toJsonNode(final RequestBody body) {
		return new ObjectMapper().valueToTree(body);
	}

	protected void removeFromNode(final JsonNode node, final String... pathItems) {
		if (pathItems.length > 0) {
			JsonNode currentNode = node;
			String nodeName = pathItems[0];
			int index = 0;
			while (index < (pathItems.length - 1)) {
				currentNode = node.get(nodeName);
				index++;
				nodeName = pathItems[index];
			}
			if (currentNode.isObject()) {
				final ObjectNode objectNode = (ObjectNode) currentNode;
				objectNode.remove(nodeName);
			}
		}
	}
}
