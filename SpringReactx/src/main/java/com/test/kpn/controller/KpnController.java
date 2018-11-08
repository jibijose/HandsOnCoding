package com.test.kpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.kpn.model.KpnStatus;
import com.test.kpn.service.KpnService;

@RestController
public class KpnController {

	@Autowired
	private KpnService kpnService;

	@GetMapping("/kpn/get/status/{travelFromPlace}/{travelToPlace}/{travelDateFormatted}")
	public KpnStatus fetchBusStatus(@PathVariable("travelFromPlace") String travelFromPlace,
			@PathVariable("travelToPlace") String travelToPlace,
			@PathVariable("travelDateFormatted") String travelDateFormatted) {
		return kpnService.fetchKpnStatus(travelFromPlace, travelToPlace, travelDateFormatted).get();
	}
}
