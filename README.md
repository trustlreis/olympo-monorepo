# olympo-monorepo

## Getting NEXUS up and running

```
docker-compose up
```

### Admin Password 1st time

You need to get the temporary password inside the `nexus-data` volume (`/nexus-data` folder inside the container).

```
cd /nexus-data
cat admin.password
```

### Configuring NEXUS first time

* Login to `http://localhost:8081`
* Click on the login button on the top-right corner
* Username: `admin` Password: `the_temp_password_inside_admin.password`
* Set a new password for admin
* Follow the wizard with `next-next-finish`

### Add the password credentials to you Maven `settings.xml`

* Add to your `$HOME/.m2/settings.xml`

```
<servers>

    <server>
        <id>nexus-snapshots</id>
        <username>admin</username>
        <password>you-new-nexus-admin-password</password>
    </server>
    <server>
        <id>nexus-releases</id>
        <username>admin</username>
        <password>you-new-nexus-admin-password</password>
    </server>

</servers>
```