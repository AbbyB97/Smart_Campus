package com.example.smart_campus.smartcampus.API;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class Submissions
{
  private String Dbms;
  private java.util.Date updated;
  private String Java;
  private java.util.Date created;
  private String objectId;
  private String Embedded;
  private String ownerId;
  private Integer sortit;
  private String Management;
  public String getDbms()
  {
    return Dbms;
  }

  public void setDbms( String Dbms )
  {
    this.Dbms = Dbms;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getJava()
  {
    return Java;
  }

  public void setJava( String Java )
  {
    this.Java = Java;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getEmbedded()
  {
    return Embedded;
  }

  public void setEmbedded( String Embedded )
  {
    this.Embedded = Embedded;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public Integer getSortit()
  {
    return sortit;
  }

  public void setSortit( Integer sortit )
  {
    this.sortit = sortit;
  }

  public String getManagement()
  {
    return Management;
  }

  public void setManagement( String Management )
  {
    this.Management = Management;
  }

                                                    
  public Submissions save()
  {
    return Backendless.Data.of( Submissions.class ).save( this );
  }

  public Future<Submissions> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Submissions> future = new Future<Submissions>();
      Backendless.Data.of( Submissions.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Submissions> callback )
  {
    Backendless.Data.of( Submissions.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Submissions.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Submissions.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Submissions.class ).remove( this, callback );
  }

  public static Submissions findById( String id )
  {
    return Backendless.Data.of( Submissions.class ).findById( id );
  }

  public static Future<Submissions> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Submissions> future = new Future<Submissions>();
      Backendless.Data.of( Submissions.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Submissions> callback )
  {
    Backendless.Data.of( Submissions.class ).findById( id, callback );
  }

  public static Submissions findFirst()
  {
    return Backendless.Data.of( Submissions.class ).findFirst();
  }

  public static Future<Submissions> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Submissions> future = new Future<Submissions>();
      Backendless.Data.of( Submissions.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Submissions> callback )
  {
    Backendless.Data.of( Submissions.class ).findFirst( callback );
  }

  public static Submissions findLast()
  {
    return Backendless.Data.of( Submissions.class ).findLast();
  }

  public static Future<Submissions> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Submissions> future = new Future<Submissions>();
      Backendless.Data.of( Submissions.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Submissions> callback )
  {
    Backendless.Data.of( Submissions.class ).findLast( callback );
  }

  public static BackendlessCollection<Submissions> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Submissions.class ).find( query );
  }

  public static Future<BackendlessCollection<Submissions>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Submissions>> future = new Future<BackendlessCollection<Submissions>>();
      Backendless.Data.of( Submissions.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Submissions>> callback )
  {
    Backendless.Data.of( Submissions.class ).find( query, callback );
  }
}