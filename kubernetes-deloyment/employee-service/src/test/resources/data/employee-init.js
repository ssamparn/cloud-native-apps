// create db
db = db.getSiblingDB('employee');

// create user
db.createUser({
    user: 'employee_user',
    pwd: 'employee_password',
    roles:[
        {
            role: 'readWrite',
            db: 'employee'
        }
    ]
});

// create collection
db.createCollection('employee');

// create docs
db.employee.insertMany([
    {
        _id: 50882,
        name: 'Sashank',
        email: 'sashank1703@gmail.com',
        age: '32'
    },
    {
       _id: 50883,
       name: 'Aparna',
       email: 'aparna.samal7@gmail.com',
       age: '30'
    },
    {
       _id: 50884,
       name: 'Monalisa',
       email: 'monalisa.samantray11@gmail.com',
       age: '34'
    }
]);