var idSite = 5;
var matomoTrackingApiUrl = 'https://analytics.ossupstream.org/matomo.php';

var _paq = window._paq = window._paq || [];  
_paq.push(['setTrackerUrl', matomoTrackingApiUrl]);
_paq.push(['setSiteId', idSite]);
_paq.push(['trackPageView']);
_paq.push(['enableLinkTracking']);  