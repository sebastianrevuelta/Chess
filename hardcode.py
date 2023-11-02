from sqlalchemy import create_engine


# ruleid: python-sqlalchemy-hardcoded-secret
create_engine("postgres://user:pass@localhost:5432/biz")
# ruleid: python-sqlalchemy-hardcoded-secret
create_engine("mysql+pymysql://root:aaaa@localhost:3306/aaa?charset=utf8")
# ok: python-sqlalchemy-hardcoded-secret
create_engine("mysql+pymysql://<user>:aaaa@localhost:3306/TrainNote?charset=utf8")
# ok: python-sqlalchemy-hardcoded-secret
create_engine("mysql+pymysql://<user>@localhost:3306/aaa?charset=utf8")
