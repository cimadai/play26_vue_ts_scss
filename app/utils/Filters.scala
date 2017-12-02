package utils

import javax.inject.Inject

import play.filters.cors.CORSFilter
import play.api.http.{DefaultHttpFilters, EnabledFilters}
import play.filters.csrf.CSRFFilter

class Filters @Inject()(enabledFilters: EnabledFilters, corsFilter: CORSFilter, csrfFilter: CSRFFilter)
  extends DefaultHttpFilters(enabledFilters.filters :+ corsFilter :+ csrfFilter: _*)
