# Contributing guide

**First of all, thank you for taking the time to contribute to WildFly and to the https://wildfly.org website!** We value every contribution, whether it's a document edit or a major revision.

## Legal

All contributions to this repository are licensed under the [Creative Commons Attribution 3.0](https://creativecommons.org/licenses/by/3.0/) license.

All contributions are subject to the [Developer Certificate of Origin (DCO)](https://developercertificate.org/).
The DCO text is also included verbatim in the [dco.txt](dco.txt) file in the root directory of the repository.

### Compliance with Laws and Regulations

All contributions must comply with applicable laws and regulations, including U.S. export control and sanctions restrictions.
For background, see the Linux Foundation’s guidance:
[Navigating Global Regulations and Open Source: US OFAC Sanctions](https://www.linuxfoundation.org/blog/navigating-global-regulations-and-open-source-us-ofac-sanctions).

## Issues

You can find all issues on the [Github Issues page](https://github.com/wildfly/wildfly.org/issues). In addition, the [WildFly Content GitHub project](https://github.com/orgs/wildfly/projects/1) provides an organized view of issues focused on desirable new site content. 

Once you have selected an issue you'd like to work on, make sure it's not already assigned to someone else.

Check for any issues with the `good-first-issue` label. These are a triaged set of issues that are great for getting started on our project.

If there isn't one already, for all but trivial changes please create a GitHub issue before beginning work.

**Discuss First**: For significant changes, start a [Zulip wildfly-developers discussion](https://wildfly.zulipchat.com/#narrow/channel/174184-wildfly-developers) to establish consensus about your change before beginning work.

## Getting Started

These instructions will help you get a copy of the wildfly.org website up and running on your local machine for development purposes.

1. Install a full Java development environment
    * JDK 21+
    * Maven

2. Fork the [project repository](https://github.com/wildfly/wildfly.org), then clone your fork:

        git clone git@github.com:YOUR_USER_NAME/wildfly.org.git

3. Change into the project directory:

        cd wildfly.org

4. Build the site and make it available on a local server:

        mvn quarkus:dev

5. Now browse to http://localhost:8080

## Submitting changes

- **Validate your rendered change locally**:
    - `mvn quarkus:dev`
    - Browse to `http://localhost:8080`
- **Create a PR**: Submit your changes via a pull request from your fork.
- **Wait for Review**: A maintainer will review your PR and might ask for changes or clarifications.
- **Respond to Feedback**: Address any feedback to get your PR approved and merged.

## Common Types of Contributions

Following are details on how to make common types of contributions.

### Writing a News post

To write a [News](https://wildfly.org/news) post:

- If you are a new author:
    - If you wish to have a picture associated with your posts, please add a file to the [authors subdir in the assets directory](https://github.com/wildfly/wildfly.org/tree/main/public/assets/img/authors). This is optional but encouraged.
    - Create an author entry in [data/authors.yaml](https://github.com/wildfly/wildfly.org/tree/main/data/authors.yaml)
        - If you added an author photo, include the `avatar` field with a value that is the name of the author's picture file in the [authors subdir](https://github.com/wildfly/wildfly.org/tree/main/public/assets/img/authors) in the assets directory.
- Create a news entry under [content/posts](https://github.com/wildfly/wildfly.org/tree/main/content/posts)
    - The file name is `yyyy-mm-dd-slug.adoc`
- All news posts should be written in [AsciiDoc format](https://asciidoctor.org/docs/asciidoc-syntax-quick-reference/).
- Posts must include appropriate YAML front-matter. Look at the many existing files in [content/posts](https://github.com/wildfly/wildfly.org/tree/main/content/posts) to get a sense of what to do. Focus on more recent ones as they will better reflect current best practices.
- Build the site by running `mvn quarkus:dev` and browse to http://localhost:8080 to validate that your post renders properly and all links work.
- Create a pull request against the main branch.

> [!TIP]
> In the YAML front matter, include **both** a `synopsis` and a `description` attribute with a one or two sentence description of the content. Both attributes can have the same value.
>
> Why **both** a `synopsis` and a `description` attribute? It seems that Roq uses the `synopsis` data in the https://wildfly.org/news list of post summaries, while it uses `description` in the https://wildfly.org/feed.xml RSS feed information.
>
> If there is no `synopsis` attribute, the post's entry on the https://wildfly.org/news list will be created from the initial content of the post.

### Using External Content for a News Post

WildFly contributors are encouraged to add entries in the https://wildfly.org/news section referencing blog posts and vlogs they have published on other sites. Vlogs published to the [WildFlyAS YouTube channel](https://www.youtube.com/@WildFlyAS) definitely should have a corresponding news entry to publicize the vlog.

Adding such an entry is simple -- add an adoc file in [content/posts](https://github.com/wildfly/wildfly.org/tree/main/content/posts) much as you would for a normal news post. But, **the file doesn't need to have anything other than YAML front matter.**

In the YAML front matter, include an `external-link` attribute with the URL of the external content.

- An [external blog post example](https://github.com/wildfly/wildfly.org/tree/main/content/posts)
- A [vlog example](https://github.com/wildfly/wildfly.org/tree/main/content/posts2025-08-08-wildfly-gemini-cli.adoc)

### Adding a Guide

Discuss in the [Zulip wildfly-developers channel](https://wildfly.zulipchat.com/#narrow/channel/174184-wildfly-developers) before doing significant work on a guide. Compared to most other contributions, guides require a lot of effort from the author and from those doing code review, so discuss first to avoid going down a wrong path.

The primary content for a guide consists of an adoc file in the [content/guides](https://github.com/wildfly/wildfly.org/tree/main/content/guides) directory. Start by making a copy of the [guide template](https://github.com/wildfly/wildfly.org/tree/main/content/guides/template.adoc).

Avoid repeating content already found in other guides. Instead, add links to those guides in the new guide's **Prerequisites** section.

>[!IMPORTANT]
> Code examples referred to in the guides are maintained in the [wildfly-extras/guides](https://github.com/wildfly-extras/guides) repository and pull requests should be submitted there.

Add an entry in the [guides.yaml](https://github.com/wildfly/wildfly.org/tree/main/data/guides.yaml) file for your new guide. This file drives the content of the overall https://wildfly.org/guides page. Find the appropriate category for your guide and add the metadata for it alphabetically within that category's section. Be sure to discuss before adding a new category. Categories should also be alphabetical, except for the initial 'Getting Started' category. 

Within a category guides can also be grouped. See the 'Cloud / Containerization' category in `guides.yaml` for an example of this.

### Adding Signing Information

WildFly maintainers are encouraged to document on the https://www.wildfly.org/contributors/pgp/ page information about public keys used to sign commits and files. To do this you need to update the `data/contributors.yaml`
file with some information about your public keys. In the examples below we will use [GnuPG Privacy Guard](https://gnupg.org/).

Generating your key and uploading your key to a remote server is beyond the scope of these instructions.

### Finding your key

```bash
gpg --list-key --keyid-format short
```

This will list your available keys.

```
/home/user/.gnupg/pubring.kbx
---------------------------------
pub   rsa4096/39B3A8E7 2021-02-16 [SC]
      6D2AF456B8CB597387901C786F29F72839B3A8E7
uid         [ultimate] James R. Perkins <jperkins@redhat.com>
sub   rsa4096/382337E8 2021-02-16 [E]
```

You will also need your fingerprint which can be retrieved with:

```bash
gpg --list-key --fingerprint
```

This produces:
```bash
pub   rsa4096 2021-02-16 [SC]
      6D2A F456 B8CB 5973 8790  1C78 6F29 F728 39B3 A8E7
uid           [ultimate] James R. Perkins <jperkins@redhat.com>
sub   rsa4096 2021-02-16 [E]
```

### Adding your information

Below is the example YAML you would add to the `data/contributors.yaml` file associated with your bio.

```yaml
- contributor: jperkins
  name: "James R. Perkins"
  bio: "Example bio"
  github: jamezp
  signing:
    - id: rsa4096/39B3A8E7
      key: 6D2AF456B8CB597387901C786F29F72839B3A8E7
      link: https://keyserver.ubuntu.com/pks/lookup?search=6D2AF456B8CB597387901C786F29F72839B3A8E7&fingerprint=on&op=index
      fingerprint: 6D2A F456 B8CB 5973 8790  1C78 6F29 F728 39B3 A8E7
```

## Thank you

Every contribution matters. For any questions, feel free to reach out.

Happy contributing!
