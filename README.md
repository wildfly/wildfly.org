wildfly-site
============

Wildfly Website


Prerequisites
-------------

The site is built using awestruct, and its dependencies are best installed using the ruby gem `bundler`. 

1. Make sure you have a modern, sane Ruby install. The best way to install Ruby is to use RVM from <https://rvm.io/>
2. Install the ruby gem `bundler` 
        
        gem install bundler
3. Install the necessary gems for the site. At the root of the site directory, run
        
        bundle install

Developing the site
-----------------

If you run Awestruct locally, it will watch for changes to your soruces and regenerate the site. Run

    awestruct -d

and browse to <http://localhost:4242>.


Publishing the site
-------------------

To stage or publish the site, you'll need to be able to upload to the `wildfly` account on `filemgmt.jboss.org`. Send your ssh key to eng-ops@redhat.com to get access.

To stage the site:

    ./publish.sh -s

The site is staged to <http://www.wildfly.org/staging>.

To push the site live:

    ./publish.sh -p


Releasing the site
------------------

NOTE: Currently we are not releasing the site, just pushing the HEAD of master live.

To release the site (create a tag, rebuild the site from scratch, and publish it to production:

    ./release.sh -s <OLD_SNAPSHOT_VERSION> -r <RELEASE_VERSION> -n <NEW_SNAPSHOT_VERSION>

This will tag the site in your local git repo, but not push the tag.

