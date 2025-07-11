# Wildfly.org Website Based on Roq

## Getting Started

These instructions will get you a copy of the wildfly.org website up and running on your local machine for development purposes.

### Installation

[Quarkus Roq static site generator documentation](https://iamroq.com/).

1. Install a full Java development environment
    * JDK 17+
    * Maven

2. Fork the [project repository](https://github.com/wildfly/wildfly.org), then clone your fork:

        git clone git@github.com:YOUR_USER_NAME/wildfly.org.git

3. Change into the project directory:

        cd wildfly.org

4. Build the site and make it available on a local server:

        mvn quarkus:dev

5. Now browse to http://localhost:8080

## Writing a News post

To write a News post:

- If you are a new author:
  - If you wish to have a picture associated with your posts, please add a file to the [authors subdir in the assets directory](https://github.com/wildfly/wildfly.org/tree/master/public/assets/img/authors). This is optional but encouraged.
  - Create an author entry in [data/authors.yaml](https://github.com/wildfly/wildfly.org/tree/master/data/authors.yaml)
      - If you added an author photo, include the `avatar` field with a value that is the name of the author's picture file in [authors subdir](https://github.com/wildfly/wildfly.org/tree/master/public/assets/img/authors) in the assets directory.
- Create a news entry under [content/posts](https://github.com/wildfly/wildfly.org/tree/master/content/posts)
    - The file name is `yyyy-mm-dd-slug.adoc`
- All news posts should be written in [AsciiDoc format](https://asciidoctor.org/docs/asciidoc-syntax-quick-reference/).
- Create a pull request against the master branch.

## Adding Signing Information

To create information about your public keys used to sign commits and files, you need to update the `data/contributors.yaml`
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

## Contributing

Please read [CONTRIBUTING.md](https://github.com/wildfly/wildfly.org/blob/master/contribute.md) for details on the process for submitting pull requests to us.

**Important:** Code examples referred to in the guides are maintained in the [wildfly-extras/guides](https://github.com/wildfly-extras/guides) repository and pull requests should be submitted there.


## License

This website is licensed under the [Creative Commons Attribution 3.0](https://creativecommons.org/licenses/by/3.0/) license.
