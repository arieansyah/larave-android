<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Booking extends Model
{
  protected $table = 'bookings';
  protected $primaryKey = 'jadwal_id';

  protected $fillable = ['jadwal_id', 'status', 'nama', 'no_hanphone', 'username'];
}
