// create db
db = db.getSiblingDB('job');

// create user
db.createUser({
    user: "root",
    pwd: "rootpassword",
    roles:[
        {
            role: "readWrite",
            db: "job"
        }
    ]
});

// create collection
db.createCollection('job');

// create docs
db.job.insertMany([
    {
        description: "senior java dev",
        company: "amazon",
        skills: [ "java", "spring", "docker" ],
        salary: 100000,
        isRemote: false
    },
    {
        description: "junior java dev",
        company: "apple",
        skills: [ "java" ],
        salary: 50000,
        isRemote: false
    },
    {
        description: "scrum master",
        company: "google",
        skills: [ "agile", "jira" ],
        salary: 60000,
        isRemote: true
    },
    {
        description: "director of engineering",
        company: "microsoft",
        skills: [ "java", "jira", "project" ],
        salary: 150000,
        isRemote: true
    }
]);